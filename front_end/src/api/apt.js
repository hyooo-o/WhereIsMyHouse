import { apiInstance } from "./index.js";

const api = apiInstance();

async function getAptList(success, fail) {
  await api.get(`/apt/list`).then(success).catch(fail);
}

export { getAptList }