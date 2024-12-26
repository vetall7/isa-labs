export class UserModel {
  public readonly name: string;
  public readonly email: string | null;
  public readonly role: "USER" | "ADMIN";

  constructor(name: string, email: string, role: "USER" | "ADMIN") {
    this.name = name;
    this.email = email;
    this.role = role;
  }
}
