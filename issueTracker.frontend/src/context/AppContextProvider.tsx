import React, {
	createContext,
	FC,
	ReactNode,
	useEffect,
	useReducer,
} from 'react';
import { appState, themeType } from '../type';

type appContextType = {
	appState: appState;
	changeTheme: (theme: themeType) => void;
	changeEmail: (email: string) => void;
	changeIsAuth: (isAuth: boolean) => void;
	changeAccessToken: (accessToken: string) => void;
};

type actionType = {
	type: string;
	payload: string | boolean | themeType;
};

const EMAIL = 'email';
const TOKEN = 'accessToken';
const THEME = 'theme';
const AUTH = 'auth';

const defaultState: appState = {
	email: '',
	accessToken: '',
	isAuth: false,
	theme: 'dark',
};

export const AppContext = createContext<appContextType>({
	appState: defaultState,
	changeAccessToken: () => {},
	changeEmail: () => {},
	changeIsAuth: () => {},
	changeTheme: () => {},
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
		case THEME:
			return { ...state, theme: action.payload as themeType };
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
	const changeTheme = (theme: themeType): void => {
		dispatch({ type: THEME, payload: theme });
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
				changeTheme,
			}}
		>
			{children}
		</Provider>
	);
};

export default AppContextProvider;
