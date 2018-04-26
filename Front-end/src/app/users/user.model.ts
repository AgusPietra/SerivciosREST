export class User {
  public userName: string;
  public password: string;
  public email: string;

  constructor(userName: string, password: string, email: string) {
    this.userName = userName;
    this.password = password;
    this.email = email;
  }
}
