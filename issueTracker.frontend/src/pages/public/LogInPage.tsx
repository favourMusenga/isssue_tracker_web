import {
	Button,
	FormControl,
	FormLabel,
	Input,
	Center,
	VStack,
	Box,
	Heading,
	FormErrorMessage,
	Text,
	useColorModeValue,
} from '@chakra-ui/react';
import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import useAuth from '../../hooks/useAuth';

type loginForm = {
	email: string;
	password: string;
};

const LogInPage: React.FC = () => {
	const {
		handleSubmit,
		register,
		reset,
		formState: { errors, isSubmitting },
	} = useForm<loginForm>();

	const { login } = useAuth();

	const navigation = useNavigate();

	const [loginError, setLoginError] = useState<string | null>(null);

	const onSubmit = (values: loginForm) => {
		return new Promise<void>((resolve) => {
			login(values.email, values.password)
				.then(() => {
					resolve();
					reset();
					setLoginError(null);

					navigation('/');
				})
				.catch((e) => {
					setLoginError(() => e.message);
					resolve();
				});
		});
	};

	return (
		<Center width="100vw" h="100vh">
			<Box
				border="1px"
				borderColor="gray.600"
				bg={useColorModeValue('gray.50', 'gray.800')}
			>
				<form onSubmit={handleSubmit(onSubmit)}>
					<VStack p="3" spacing="4">
						<Heading textTransform="uppercase">issue tracker</Heading>
						<FormControl isRequired>
							<FormLabel htmlFor="email">Email address</FormLabel>
							<Input
								id="email"
								type="email"
								placeholder="email...."
								{...register('email', {
									required: 'email is required',
								})}
							/>
							<FormErrorMessage>
								{errors.email && errors.email.message}
							</FormErrorMessage>
						</FormControl>
						<FormControl isRequired>
							<FormLabel htmlFor="password">password</FormLabel>
							<Input
								id="password"
								type="password"
								placeholder="password...."
								{...register('password', {
									required: 'password is required',
								})}
							/>
							<FormErrorMessage>
								{errors.password && errors.password.message}
							</FormErrorMessage>
						</FormControl>
						{loginError && (
							<Box>
								<Text color="red">{loginError}</Text>
							</Box>
						)}
						<Button
							colorScheme="whatsapp"
							type="submit"
							isLoading={isSubmitting}
						>
							log in
						</Button>
					</VStack>
				</form>
			</Box>
		</Center>
	);
};

export default LogInPage;
