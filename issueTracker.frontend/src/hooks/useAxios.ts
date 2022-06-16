import { useContext } from 'react';
import axios, { AxiosError } from 'axios';
import { AppContext } from '../context/AppContextProvider';

const useAxios = () => {
	const { appState } = useContext(AppContext);
	const { accessToken } = appState;
	const axiosInstance = axios.create({
		baseURL: 'https://zesco99-issue-tracker.herokuapp.com/',
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
				throw new Error('not authorized');
			}
		}
	);
	return axiosInstance;
};

export default useAxios;
