import {
	Box,
	Button,
	Center,
	FormControl,
	FormErrorMessage,
	FormLabel,
	GridItem,
	Heading,
	Input,
	SimpleGrid,
	Text,
	useColorModeValue,
	useToast,
} from '@chakra-ui/react';
import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import useAxios from '../../hooks/useAxios';
import { ILocation } from '../../type';

const LocationForm: React.FC = () => {
	const {
		handleSubmit,
		reset,
		register,
		formState: { errors, isSubmitting },
	} = useForm<ILocation>();

	const axios = useAxios();
	const [loginError, setLoginError] = useState<string | null>(null);
	const toast = useToast();

	const onSubmit = (values: ILocation) => {
		return new Promise<void>((resolve) => {
			axios
				.post('/api/location', { ...values })
				.then((res) => {
					reset();
					setLoginError(null);
					toast({
						title: 'Location added',
						description: 'You have added a new location successfully',
						isClosable: true,
						duration: 9000,
						status: 'success',
						position: 'bottom-right',
					});
					resolve();
				})
				.catch((err) => {
					setLoginError(() => err.message);
					resolve();
				});
		});
	};
	return (
		<Center w="full" bg={useColorModeValue('white', 'gray.700')} padding={5}>
			<form onSubmit={handleSubmit(onSubmit)}>
				<Heading>Add Location</Heading>
				<SimpleGrid column="2" columnGap={4} rowGap={5} marginTop={3}>
					<GridItem colSpan={2}>
						<FormControl>
							<FormLabel htmlFor="physicalAddress">Physical address</FormLabel>
							<Input
								type="text"
								id="physicalAddress"
								placeholder="Enter physical address..."
								required
								{...register('physicalAddress', {
									required: 'physical address required',
								})}
							/>
							<FormErrorMessage>
								{errors.physicalAddress && errors.physicalAddress.message}
							</FormErrorMessage>
						</FormControl>
					</GridItem>
					<GridItem colSpan={1}>
						<FormControl>
							<FormLabel htmlFor="town">Town</FormLabel>
							<Input
								type="text"
								id="town"
								placeholder="Enter town..."
								required
								{...register('town', { required: 'Town is required' })}
							/>
							<FormErrorMessage>
								{errors.town && errors.town.message}
							</FormErrorMessage>
						</FormControl>
					</GridItem>
					<GridItem colSpan={1}>
						<FormControl>
							<FormLabel htmlFor="zone">Zone</FormLabel>
							<Input
								type="number"
								id="zone"
								placeholder="Enter zone..."
								required
								{...register('zone', { required: 'zone is required' })}
							/>
							<FormErrorMessage>
								{errors.town && errors.town.message}
							</FormErrorMessage>
						</FormControl>
					</GridItem>
					<GridItem colSpan={1}>
						<Button
							type="submit"
							w={'full'}
							colorScheme={'whatsapp'}
							isLoading={isSubmitting}
						>
							Add location
						</Button>
					</GridItem>
					<GridItem colSpan={1}>
						<Button type="reset" w={'full'} colorScheme={'red'}>
							Clear
						</Button>
					</GridItem>
					{loginError && (
						<Box>
							<Text color="red">{loginError}</Text>
						</Box>
					)}
				</SimpleGrid>
			</form>
		</Center>
	);
};

export default LocationForm;
