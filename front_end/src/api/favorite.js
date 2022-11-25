import { apiInstance } from "./index.js";

const api = apiInstance();

async function getFavorite(favorite, success, fail) {
    await api.get(`/user/favorite/${favorite.userId}/${favorite.aptCode}`).then(success).catch(fail);
}

async function addFavorite(favorite, success, fail) {
    await api.post(`/user/favorite`, JSON.stringify(favorite)).then(success).catch(fail);
}

async function deleteFavorite(favorite, success, fail) {
    console.log(favorite);
    await api.delete(`/user/favorite`, {data: JSON.stringify(favorite) }).then(success).catch(fail);
}

async function getFavoriteInfo(userId, success, fail) {
    await api.get(`/user/favorite/${userId}`).then(success).catch(fail);
}

export { getFavorite, addFavorite, deleteFavorite, getFavoriteInfo }