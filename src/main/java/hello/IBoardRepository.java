package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IBoardRepository extends MongoRepository<Board, String> {

    Board findByUserNameAndBoardName(String userName, String boardName);
    List<Board> findByUserName(String userName);
    void deleteByUserNameAndBoardName(String userName, String boardName);
}
