package com.rb.module.kafka.consumers;

import com.rb.module.board.entity.Board;
import com.rb.module.board.service.IBoardService;
import com.rb.module.interest.entity.Interest;
import com.rb.module.interest.service.IInterestService;
import com.rb.module.user.service.IUserService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Base64;
import java.util.List;

@Component
public class KafkaConsumers {

    private IUserService userService;
    private IBoardService boardService;
    private IInterestService interestService;

    @Autowired
    public KafkaConsumers(IUserService userService, IBoardService boardService, IInterestService interestService) {
        this.userService = userService;
        this.boardService = boardService;
        this.interestService = interestService;
    }

    @KafkaListener(topics = "TestTopic")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        //TODO capturar excepción
        System.out.println("Received from TestTopic");
        System.out.println(cr.value());
    }

    @KafkaListener(topics = "DeleteBoardTopic")
    public void deleteBoardConsumer(ConsumerRecord<?, ?> cr) throws Exception {
        //TODO capturar excepción
        Board board = (Board)SerializationUtils.deserialize(Base64.getDecoder().decode(cr.value().toString()));
        boardService.deleteAllByUserNameAndBoardName(board.userName, board.boardName);
        System.out.println("Deleted board: " + board.boardName + " from user: " + board.userName);
    }

    @KafkaListener(topics = "SetNewBoardTopic")
    public void setNewBoardConsumer(ConsumerRecord<?, ?> cr) throws Exception {
        //TODO capturar excepción
        Board board = (Board)SerializationUtils.deserialize(Base64.getDecoder().decode(cr.value().toString()));

        System.out.println("Saving new board");

        Board exsistingBoard = this.boardService.findByUserNameAndBoardName(board.userName, board.boardName);

        if (exsistingBoard != null)
            this.boardService.deleteAllByUserNameAndBoardName(board.userName, board.boardName);
        this.boardService.save(board);
        this.interestService.setNewInterests(board.interests);
        System.out.println("Saved new board named: " + board.getBoardName() + " from user: " + board.getUserName());
    }

    @KafkaListener(topics = "UpdateBoardTopic")
    public void updateBoardConsumer(ConsumerRecord<?, ?> cr) throws Exception {
        //TODO capturar excepción
        List<Board> boards = (List<Board>)SerializationUtils.deserialize(Base64.getDecoder().decode(cr.value().toString()));

        Board newBoard = boards.get(0);
        Board oldBoard = boards.get(1);
        Board exsistingBoard = this.boardService.findByUserNameAndBoardName(oldBoard.userName, oldBoard.boardName);
        if (exsistingBoard == null) {
            this.boardService.save(newBoard);
        }
        else {
            this.boardService.updateBoardOfUser(newBoard, oldBoard.boardName);//boardName tiene el nombre original, board.boardName puede tener un nombre nevo
        }
        this.interestService.setNewInterests(newBoard.interests);

        System.out.println("Updated board named: " + newBoard.getBoardName() + " from user: " + newBoard.getUserName() +
        ". Original name was: " + oldBoard.getBoardName());
    }

    @KafkaListener(topics = "getInterestAssociatedActionsTopic")
    public void getInterestAssociatedActionsConsumer(ConsumerRecord<?, ?> cr) throws Exception {
        //TODO capturar excepción
        String interestName = cr.value().toString();
        System.out.println("Accessed content of: " + interestName);
        Interest interestItem = this.interestService.findFirstByInterestName(interestName);
        if(interestItem == null) {
            interestItem = new Interest(interestName);//El interés fue borrado de la tabla, porque hace mucho que no
            // se consultaba
            System.out.println("Added interest: "+ interestName);
        }
        if(interestItem.lastTimeUpdatedMinutesAgo() > 5)
            interestItem.setAsked();//Solo lo fijo para update si se actualizó hace al menos 5 minutos por última vez.
        //Porque la primera vez que se pregunta por un interés, o la primera vez en al menos el tiempo mínimo entre
        //requests a twitter, no quiero que quede en asked después de la primera consulta.
        this.interestService.save(interestItem);//TODO, hacer úptimo, solo actualizar la indicación de que se consultó
    }
}
