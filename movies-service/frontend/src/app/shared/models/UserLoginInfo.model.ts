export class UserLoginInfoModel {
    public readonly name : string;
    public readonly password: string;

    constructor(name : string, password: string) {
        this.name = name;
        this.password = password;
    }
}
