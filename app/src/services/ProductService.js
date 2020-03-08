import Service from "./Service";

export default class ProductService extends Service {
  async getPorducts() {
    let userId = localStorage.getItem("userId");
    if (userId) {
      let resp = await this.get(
        `http://localhost:9080/v1/product?userId=${userId}`
      );
      return resp;
    } else {
      return null;
    }
  }
  async save(requestBody){
    let userId = localStorage.getItem("userId");
    if (userId) {
      try {
        let resp = await this.post(
          `http://localhost:9080/v1/product?userId=${userId}`,
          requestBody
        );
        return resp;
      } catch (error) {
        
      }
    }
  }
  async remove(productId){
    let userId = localStorage.getItem("userId");
    if (userId) {
      try {
        let resp = await this.delete(
          `http://localhost:9080/v1/product?id=${productId}&userId=${userId}`
        );
        return resp;
      } catch (error) {
        
      }
    }
  }
}
