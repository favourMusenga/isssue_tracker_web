import { Alert, AlertIcon, Container, Text } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import UserForm from '../../components/user/UserForm';
import useAppContext from '../../hooks/useAppContext';

const AddUserPage: React.FC = () => {
	const [isNotAllowed, setIsNotAllowed] = useState<boolean>(false);

	const {
		appState: { role },
	} = useAppContext();

	useEffect(() => {
		setIsNotAllowed(() => role.toLowerCase() !== 'supervisor');
	}, []);
	return (
		<Container p="0">
			{isNotAllowed && (
				<Alert status="error" variant="solid" mb="5">
					<AlertIcon />
					<Text>You are not allowed to add user</Text>
				</Alert>
			)}
			<UserForm isNotAllowed={isNotAllowed} />
		</Container>
	);
};

export default AddUserPage;
