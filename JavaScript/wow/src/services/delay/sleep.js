export default (tempo) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve();
    }, tempo);
  });
};
