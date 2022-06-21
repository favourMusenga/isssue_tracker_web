import { useContext } from 'react';
import { AppContext } from '../context/AppContextProvider';

const useAppContext = () => {
	const appContext = useContext(AppContext);

	return appContext;
};

export default useAppContext;
