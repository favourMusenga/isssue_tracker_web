import { useState } from 'react';
import { AppRoutes } from './routes';
import './App.css';
import AppContextProvider from './context/AppContextProvider';

function App() {
	return (
		<AppContextProvider>
			<AppRoutes />
		</AppContextProvider>
	);
}

export default App;
