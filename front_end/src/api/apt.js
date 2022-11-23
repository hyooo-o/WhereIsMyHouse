import { apiInstance } from "./index.js";

const api = apiInstance();

async function getAptList(loc, success, fail) {
  await api.get(`/apt/list?neLat=${loc.neLat}&neLng=${loc.neLng}&swLat=${loc.swLat}&swLng=${loc.swLng}`).then(success).catch(fail);
}

async function getAptImg(aptCode, keyword, success, fail) {
  await api.get(`/apt/image/${aptCode}/${keyword}`).then(success).catch(fail);
}

async function getChartData(aptCode, success, fail) {
  await api.get(`/apt/chart/${aptCode}`).then(success).catch(fail);
}

async function getAptDeal(aptCode, success, fail) {
  await api.get(`/apt/deal/${aptCode}`).then(success).catch(fail);
}

export { getAptList, getChartData, getAptImg, getAptDeal }