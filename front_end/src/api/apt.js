import { apiInstance } from "./index.js";

const api = apiInstance();

async function getAptList(loc, success, fail) {
  await api.get(`/apt/list?neLat=${loc.neLat}&neLng=${loc.neLng}&swLat=${loc.swLat}&swLng=${loc.swLng}`).then(success).catch(fail);
}

async function getApt(aptCode, success, fail) {
  await api.get(`/apt/search/${aptCode}`).then(success).catch(fail);
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

async function getAptSearch(apartmentName, success, fail) {
  await api.get(`/apt/search/apt/${apartmentName}`).then(success).catch(fail);
}

async function getDongSearch(dong, success, fail) {
  await api.get(`/apt/search/dong/${dong}`).then(success).catch(fail);
}

export { getAptList, getChartData, getAptImg, getAptDeal, getAptSearch, getDongSearch, getApt }
