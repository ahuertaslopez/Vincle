import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/items',
});

export const getItems = () => api.get('');
export const getItem = (id) => api.get(`/${id}`);
export const createItem = (item) => api.post('', item);
export const updateItem = (id, item) => api.put(`/${id}`, item);
export const deleteItem = (id) => api.delete(`/${id}`);