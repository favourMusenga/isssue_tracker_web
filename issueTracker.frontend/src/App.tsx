import { useState } from 'react';
import { AppRoutes } from './routes';
import './App.css';
import AppContextProvider from './context/AppContextProvider';
import { QueryClient, QueryClientProvider } from 'react-query';

function App() {
	const queryClient = new QueryClient({
		defaultOptions: { queries: { refetchInterval: 5000 } },
	});
	return (
		<QueryClientProvider client={queryClient}>
			<AppContextProvider>
				<AppRoutes />
			</AppContextProvider>
		</QueryClientProvider>
	);
}

export default App;
