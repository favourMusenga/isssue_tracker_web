import React from 'react';
import useAxios from '../../hooks/useAxios';

const LogInPage: React.FC = () => {
	const axios = useAxios();

	return (
		<div>
			LogInPage{' '}
			<button
				onClick={(e) => {
					axios
						.get('/status')
						.then(() => {})
						.catch((err) => console.log(err));
				}}
			>
				click
			</button>
		</div>
	);
};

export default LogInPage;
