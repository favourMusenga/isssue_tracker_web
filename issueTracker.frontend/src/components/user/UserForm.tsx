import {
	Button,
	Center,
	FormControl,
	FormErrorMessage,
	FormLabel,
	GridItem,
	Heading,
	Input,
	Select,
	SimpleGrid,
	useColorModeValue,
	useBreakpointValue,
	useToast,
} from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import useAxios from '../../hooks/useAxios';
import { IRole, IUserRequest } from '../../type';

const UserForm: React.FC = () => {
	const [roles, setRoles] = useState<IRole[]>([]);
	const colSpan = useBreakpointValue({ base: 2, sm: 1 });
	const axios = useAxios();
	const toast = useToast();
	const {
		register,
		handleSubmit,
		reset,
		setValue,
		formState: { errors, isSubmitting },
	} = useForm<IUserRequest>();

	const onSubmit = (values: IUserRequest) => {
		setValue('password', 'test1234');
		return new Promise<void>((resolve) => {
			axios
				.post('/api/user', { ...values })
				.then(() => {
					toast({
						title: 'User added',
						description: 'You have added a new user successfully',
						isClosable: true,
						duration: 9000,
						status: 'success',
						position: 'bottom-right',
					});
					resolve();
				})
				.catch((err) => {
					console.log(err);
				});
			resolve();
		});
	};

	useEffect(() => {
		axios.get('/api/role').then((res) => setRoles(() => res.data.data));
	}, []);
	return (
		<Center w="full" bg={useColorModeValue('white', 'gray.700')} padding={5}>
			<form onSubmit={handleSubmit(onSubmit)}>
				<Heading>Add User</Heading>
				<SimpleGrid column="2" columnGap={4} rowGap={5} marginTop={3}>
					<GridItem colSpan={colSpan}>
						<FormControl isRequired>
							<FormLabel htmlFor="firstName">first name</FormLabel>
							<Input
								type="text"
								id="firstName"
								placeholder="Enter first name..."
								{...register('firstName', {
									required: 'first name is required',
								})}
							/>
							<FormErrorMessage>
								{errors.firstName && errors.firstName.message}
							</FormErrorMessage>
						</FormControl>
					</GridItem>
					<GridItem colSpan={colSpan}>
						<FormControl>
							<FormLabel htmlFor="middleName">middle name</FormLabel>
							<Input
								type="text"
								id="middleName"
								placeholder="Enter middle name..."
								{...register('middleName')}
							/>
						</FormControl>
					</GridItem>
					<GridItem colSpan={colSpan}>
						<FormControl isRequired>
							<FormLabel htmlFor="lastName">last name</FormLabel>
							<Input
								type="text"
								id="lastName"
								placeholder="Enter last name..."
								{...register('lastName', { required: 'last name is required' })}
							/>
							<FormErrorMessage>
								{errors.lastName && errors.lastName.message}
							</FormErrorMessage>
						</FormControl>
					</GridItem>
					<GridItem colSpan={colSpan}>
						<FormControl isRequired>
							<FormLabel htmlFor="role">role</FormLabel>
							<Select
								id="role"
								placeholder="Enter role..."
								{...register('role')}
							>
								{roles.map((role) => (
									<option key={role.id} value={role.name}>
										{role.name}
									</option>
								))}
							</Select>
						</FormControl>
					</GridItem>
					<GridItem colSpan={2}>
						<FormControl isRequired>
							<FormLabel htmlFor="email">email</FormLabel>
							<Input
								type="email"
								id="email"
								placeholder="Enter email..."
								{...register('email', { required: 'email is required' })}
							/>
							<FormErrorMessage>
								{errors.email && errors.email.message}
							</FormErrorMessage>
						</FormControl>
					</GridItem>
					<GridItem colSpan={colSpan}>
						<Button
							type="submit"
							w={'full'}
							colorScheme={'whatsapp'}
							isLoading={isSubmitting}
						>
							Add user
						</Button>
					</GridItem>
					<GridItem colSpan={colSpan}>
						<Button type="reset" w={'full'} colorScheme={'red'}>
							Clear
						</Button>
					</GridItem>
				</SimpleGrid>
			</form>
		</Center>
	);
};

export default UserForm;
