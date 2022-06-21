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
import useAppContext from '../../hooks/useAppContext';
import useAxios from '../../hooks/useAxios';
import { IEquipment, IInspectionRequest, IStatus } from '../../type';

const InspectionForm: React.FC = () => {
	const {
		register,
		handleSubmit,
		reset,
		setValue,
		formState: { errors, isSubmitting },
	} = useForm<IInspectionRequest>();
	const colSpan = useBreakpointValue({ base: 2, sm: 1 });
	const [equipments, setEquipments] = useState<IEquipment[]>([]);
	const [statuses, setStatuses] = useState<IStatus[]>([]);
	const axios = useAxios();
	const toast = useToast();
	const {
		appState: { role, email },
	} = useAppContext();

	const onSubmit = (values: IInspectionRequest) => {
		return new Promise<void>((resolve) => {
			axios
				.post('/api/inspection', {
					...values,
					comment: values.comment?.trim() === '' ? null : values.comment,
					userEmail: email,
				})
				.then(() => {
					toast({
						title: 'Inspection added',
						description: 'You have added a new inspection successfully',
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
		axios
			.get('/api/equipment')
			.then((res) => setEquipments(() => res.data.data));

		axios.get('/api/status').then((res) => setStatuses(() => res.data.data));
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
					<GridItem colSpan={colSpan}>
						<FormControl isRequired>
							<FormLabel htmlFor="date">date</FormLabel>
							<Input
								type="date"
								id="date"
								placeholder="Enter date..."
								{...register('date', { required: 'date is required' })}
							/>
							<FormErrorMessage>
								{errors.date && errors.date.message}
							</FormErrorMessage>
						</FormControl>
					</GridItem>
					<GridItem colSpan={colSpan}>
						<FormControl isRequired>
							<FormLabel htmlFor="status">status</FormLabel>
							<Select
								id="status"
								placeholder="Select status..."
								{...register('statusId', { required: 'status is required' })}
							>
								{statuses.length > 0 &&
									statuses.map((status) => (
										<option
											key={`${status.id}-${status.name}`}
											value={status.id}
										>
											{status.name}
										</option>
									))}
							</Select>
							<FormErrorMessage>
								{errors.statusId && errors.statusId.message}
							</FormErrorMessage>
						</FormControl>
					</GridItem>
					<GridItem colSpan={2}>
						<FormControl isRequired>
							<FormLabel htmlFor="equipment">Equipment</FormLabel>
							<Select
								id="equipment"
								placeholder="Select equipment..."
								{...register('equipmentId')}
							>
								{equipments.length > 0 &&
									equipments.map((equipment) => (
										<option
											key={equipment.id}
											value={equipment.id}
										>{`${equipment.name}-${equipment.location.town}-${equipment.location.zone}`}</option>
									))}
							</Select>
						</FormControl>
					</GridItem>
					<GridItem colSpan={2}>
						<FormControl isDisabled={role.toLowerCase() !== 'supervisor'}>
							<FormLabel htmlFor="comment">Comment</FormLabel>
							<Textarea
								id="comment"
								placeholder="Enter description..."
								{...register('comment')}
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
							Add inspection
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

export default InspectionForm;
