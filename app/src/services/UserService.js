
import Service from "./Service";

export default class UserService extends Service {
  async login(requestBody) {

    let resp = await this.post(`http://localhost:9080/v1/users/login`, requestBody);
    localStorage.setItem("userId", resp.data.id);
    return resp;
  }

  async save(requestBody) {
    let resp = await this.post(`http://localhost:9080/v1/users`, requestBody);
    localStorage.setItem("userId", resp.data.id);
    return resp;
  }



}
