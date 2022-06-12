import { AxiosError } from 'axios';
import { useContext } from 'react';
import { AppContext } from '../context/AppContextProvider';
import useAxios from './useAxios';

type resData = { expiresIn: string; accessToken: string; email: string };

const useAuth = () => {
	const axios = useAxios();
	const { changeEmail, changeIsAuth, changeAccessToken } =
		useContext(AppContext);

	async function login(username: string, password: string) {
		const formData = new FormData();
		formData.append('username', username);
		formData.append('password', password);
		try {
			const res = await axios.post('/login', formData, {
				headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			});
			const resData: resData = res.data;

			changeEmail(resData.email);
			changeIsAuth(true);
			changeAccessToken(resData.accessToken);
		} catch (e) {
			if (e instanceof AxiosError) {
				if (e.response?.status === 401) {
					throw new Error('invalid password or email');
				}
			} else {
				throw new Error('something went wrong!');
			}
		}
	}

	function logout() {}

	return { login, logout };
};

export default useAuth;
