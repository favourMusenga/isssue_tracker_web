import { useContext } from 'react';
import axios from 'axios';
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

	return axiosInstance;
};

export default useAxios;
