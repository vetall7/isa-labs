export class UserModel {
  public readonly id: number;
  public readonly username: string;
  public readonly email: string;
  public readonly role: "USER" | "ADMIN";

  constructor(id: number, username: string, email: string, role: "USER" | "ADMIN") {
    this.id = id;
    this.username = username;
    this.email = email;
    this.role = role;
  }
}
