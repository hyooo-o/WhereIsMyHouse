import { apiInstance } from "./index.js";

const api = apiInstance();

async function newsList(success, fail) {
  await api.get(`/news/list`).then(success).catch(fail);
}

export { newsList }
