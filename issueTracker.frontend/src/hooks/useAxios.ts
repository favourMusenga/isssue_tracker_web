import { useContext } from 'react';
import axios from 'axios';
import { AppContext } from '../context/AppContextProvider';

const useAxios = () => {
	const { appState } = useContext(AppContext);
	const { accessToken } = appState;
	const axiosInstance = axios.create({
		baseURL: 'https://zesco99-issue-tracker.herokuapp.com/api/',
		headers: {
			Authorization: `Bearer ${accessToken}`,
			'Access-Control-Allow-Origin': '*',
		},
	});

	axiosInstance.interceptors.response.use(
		function (response) {
			return response;
		},
		function (error) {
			console.log('inter' + error);
		}
	);

	return axiosInstance;
};

export default useAxios;
