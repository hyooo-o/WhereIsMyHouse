import { apiInstance } from "./index.js";

const api = apiInstance();

async function getFavorite(favoriteDto, success, fail) {
    await api.get(`/user/favorite/${favoriteDto.userId}/${favoriteDto.aptCode}`).then(success).catch(fail);
}

async function addFavorite(loc, success, fail) {
    await api.post(`/user/favorite`).then(success).catch(fail);
}

async function deleteFavorite(loc, success, fail) {
    await api.delete(`/user/favorite`).then(success).catch(fail);
}

export { getFavorite, addFavorite, deleteFavorite }