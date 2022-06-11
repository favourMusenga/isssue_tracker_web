import { Link } from '@chakra-ui/react';
import React from 'react';
import { Outlet, Link as RouterLink } from 'react-router-dom';
import { Header } from './Header';

const MainLayout: React.FC = () => {
	return (
		<>
			<Header />
			<ul style={{ display: 'flex' }}>
				<li>
					<Link as={RouterLink} to="/">
						Dashboard
					</Link>
				</li>
				<li>
					<Link as={RouterLink} to="/equipment">
						equipment
					</Link>
				</li>
				<li>
					<Link as={RouterLink} to="/inspection">
						inspection
					</Link>
				</li>
				<li>
					<Link as={RouterLink} to="/location">
						location
					</Link>
				</li>
				<li>
					<Link as={RouterLink} to="/add-user">
						add user
					</Link>
				</li>
				<li>
					<Link as={RouterLink} to="/about">
						about
					</Link>
				</li>
			</ul>
			<Outlet />
		</>
	);
};

export default MainLayout;
