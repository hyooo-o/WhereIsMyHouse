import { apiInstance } from "./index.js";

const api = apiInstance();

async function getAptList(loc, success, fail) {
  console.log(loc);
  await api.get(`/apt/list?neLat=${loc.neLat}&neLng=${loc.neLng}&swLat=${loc.swLat}&swLng=${loc.swLng}`).then(success).catch(fail);
}

export { getAptList }