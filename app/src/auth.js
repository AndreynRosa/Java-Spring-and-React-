export const isAuthenticated = () => {
  let userId = localStorage.getItem("userId");
  return userId, userId >= 0 ? true : false;
};
