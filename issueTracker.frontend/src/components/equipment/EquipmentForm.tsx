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
	Textarea,
	useBreakpointValue,
	useColorModeValue,
	useToast,
} from '@chakra-ui/react';
import { AxiosError } from 'axios';
import React, { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import useAxios from '../../hooks/useAxios';
import { IEquipmentRequest, ILocation } from '../../type';

const EquipmentForm: React.FC = () => {
	const {
		register,
		handleSubmit,
		reset,
		formState: { errors, isSubmitting },
	} = useForm<IEquipmentRequest>();
	const colSpan = useBreakpointValue({ base: 2, sm: 1 });
	const [locations, setLocations] = useState<ILocation[]>([]);
	const axios = useAxios();
	const toast = useToast();

	const onSubmit = (values: IEquipmentRequest) => {
		return new Promise<void>((resolve) => {
			axios
				.post('/api/equipment', { ...values })
				.then(() => {
					toast({
						title: 'Equipment added',
						description: 'You have added a new equipment successfully',
						isClosable: true,
						duration: 9000,
						status: 'success',
						position: 'bottom-right',
					});
					reset();
					resolve();
				})
				.catch((err) => {
					if (err instanceof AxiosError) {
						const errorData = err.response?.data.error;
						if (typeof errorData === 'string') {
							toast({
								title: 'Error',
								description: errorData,
								isClosable: true,
								duration: 9000,
								status: 'error',
								position: 'bottom-right',
							});
						}
					} else {
						toast({
							title: 'Error',
							description: 'something went wrong!!',
							isClosable: true,
							duration: 9000,
							status: 'error',
							position: 'bottom-right',
						});
					}
					resolve();
				});
		});
	};

	useEffect(() => {
		axios.get('/api/location').then((res) => setLocations(() => res.data.data));
	}, []);
	return (
		<Center
			bg={useColorModeValue('white', 'gray.700')}
			marginX="auto"
			padding={5}
		>
			<form onSubmit={handleSubmit(onSubmit)}>
				<Heading>Add Equipment</Heading>
				<SimpleGrid column="2" columnGap={4} rowGap={5} marginTop={3}>
					<GridItem colSpan={2}>
						<FormControl isRequired>
							<FormLabel htmlFor="name">Name</FormLabel>
							<Input
								type="text"
								id="name"
								placeholder="Enter name..."
								{...register('name', { required: 'name is required' })}
							/>
							<FormErrorMessage>
								{errors.name && errors.name.message}
							</FormErrorMessage>
						</FormControl>
					</GridItem>
					<GridItem colSpan={2}>
						<FormControl isRequired>
							<FormLabel htmlFor="location">Location</FormLabel>
							<Select
								id="location"
								placeholder="Select location..."
								{...register('locationId')}
							>
								{locations.length > 0 &&
									locations
										.sort()
										.map((location) => (
											<option
												key={location.id}
												value={location.id}
											>{`${location.physicalAddress}-${location.town}`}</option>
										))}
							</Select>
						</FormControl>
					</GridItem>
					<GridItem colSpan={2}>
						<FormControl>
							<FormLabel htmlFor="description">Description</FormLabel>
							<Textarea
								id="description"
								placeholder="Enter description..."
								{...register('description')}
							/>
						</FormControl>
					</GridItem>
					<GridItem colSpan={colSpan}>
						<Button
							type="submit"
							w={'full'}
							colorScheme={'whatsapp'}
							isLoading={isSubmitting}
						>
							Add equipment
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

export default EquipmentForm;
