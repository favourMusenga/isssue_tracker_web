import { Container } from '@chakra-ui/react';
import React from 'react';
import UserForm from '../../components/user/UserForm';

const AddUserPage: React.FC = () => {
	return (
		<Container p="0">
			<UserForm />
		</Container>
	);
};

export default AddUserPage;
