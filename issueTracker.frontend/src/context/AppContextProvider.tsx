import React, {
	createContext,
	FC,
	ReactNode,
	useEffect,
	useReducer,
} from 'react';
import { appState } from '../type';

type appContextType = {
	appState: appState;
	changeExpiresIn: (date: string) => void;
	changeEmail: (email: string) => void;
	changeIsAuth: (isAuth: boolean) => void;
	changeAccessToken: (accessToken: string) => void;
};

type actionType = {
	type: string;
	payload: string | boolean;
};

const EMAIL = 'email';
const TOKEN = 'accessToken';
const EXPIRES_IN = 'expireIn';
const AUTH = 'auth';

const defaultState: appState = {
	email: '',
	accessToken: '',
	isAuth: false,
	expiresIn: 'dark',
};

export const AppContext = createContext<appContextType>({
	appState: defaultState,
	changeAccessToken: () => {},
	changeEmail: () => {},
	changeIsAuth: () => {},
	changeExpiresIn: () => {},
});

const Provider = AppContext.Provider;

function getAppStateLocalStorage(): appState {
	const appStore = window.localStorage.getItem('issue-tracker-appState');

	const appState: appState = appStore ? JSON.parse(appStore) : defaultState;

	return appState;
}

function setAppStateLocalStorage(appState: appState): void {
	window.localStorage.setItem(
		'issue-tracker-appState',
		JSON.stringify(appState)
	);
}

function reducer(state: appState, action: actionType): appState {
	switch (action.type) {
		case EMAIL:
			return { ...state, email: action.payload as string };
		case AUTH:
			return { ...state, isAuth: action.payload as boolean };
		case TOKEN:
			return { ...state, accessToken: action.payload as string };
		case EXPIRES_IN:
			return { ...state, expiresIn: action.payload as string };
		default:
			return state;
	}
}

const AppContextProvider: FC<{ children: ReactNode }> = ({ children }) => {
	const initialState = getAppStateLocalStorage();

	const [appState, dispatch] = useReducer(reducer, initialState);

	const changeAccessToken = (token: string): void => {
		dispatch({ type: TOKEN, payload: token });
	};
	const changeEmail = (email: string): void => {
		dispatch({ type: EMAIL, payload: email });
	};
	const changeIsAuth = (isAuth: boolean): void => {
		dispatch({ type: AUTH, payload: isAuth });
	};
	const changeExpiresIn = (date: string): void => {
		dispatch({ type: EXPIRES_IN, payload: date });
	};

	useEffect(() => {
		setAppStateLocalStorage(appState);
	}, [appState]);

	return (
		<Provider
			value={{
				appState,
				changeAccessToken,
				changeEmail,
				changeIsAuth,
				changeExpiresIn,
			}}
		>
			{children}
		</Provider>
	);
};

export default AppContextProvider;
