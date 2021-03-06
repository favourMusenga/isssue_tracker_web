import { useContext } from 'react';
import axios, { AxiosError } from 'axios';
import { AppContext } from '../context/AppContextProvider';

const useAxios = () => {
	const { appState } = useContext(AppContext);
	const { accessToken } = appState;
	const axiosInstance = axios.create({
		baseURL: import.meta.env.VITE_API_URL,
		headers: {
			Authorization: `Bearer ${accessToken}`,
		},
	});
	axiosInstance.interceptors.response.use(
		(response) => {
			return response;
		},
		(err) => {
			if (err instanceof AxiosError) {
				if (err.response?.status === 403) throw new Error('not authorized');
				else throw err;
			}
		}
	);
	return axiosInstance;
};

export default useAxios;
