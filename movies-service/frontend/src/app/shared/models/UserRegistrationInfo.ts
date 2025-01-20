export class UserRegistrationInfoModel {
  public readonly name : string;
  public readonly email: string;
  public readonly password: string;

  constructor(name : string, password: string, email: string) {
    this.name = name;
    this.password = password;
    this.email = email;
  }
}
