import {
	Spinner,
	Table,
	TableContainer,
	Tbody,
	Td,
	Text,
	Th,
	Thead,
	Tr,
	useColorModeValue,
} from '@chakra-ui/react';
import React from 'react';
import { useQuery } from 'react-query';
import useAxios from '../../hooks/useAxios';
import { ILocation } from '../../type';

const LocationTable: React.FC = () => {
	let counter = 1;
	const axios = useAxios();

	async function getLocation(): Promise<ILocation[]> {
		const res = await axios.get('/api/location');
		const resData = res.data.data;

		return resData;
	}

	const { data, isLoading, isError } = useQuery('fetch-location', getLocation);
	return (
		<TableContainer
			marginTop={'10'}
			bg={useColorModeValue('white', 'gray.700')}
		>
			<Table variant="simple" colorScheme="whatsapp">
				<Thead>
					<Tr>
						<Th isNumeric>No</Th>
						<Th>Physical Address</Th>
						<Th>Town</Th>
						<Th isNumeric>Zone</Th>
					</Tr>
				</Thead>
				<Tbody>
					{data?.length === 0 && (
						<Tr>
							<Td colSpan={4} textAlign="center">
								<Text> no data to show</Text>
							</Td>
						</Tr>
					)}

					{isLoading && (
						<Tr>
							<Td colSpan={4} textAlign="center">
								<Spinner colorScheme="whatsapp" />
							</Td>
						</Tr>
					)}
					{isError && (
						<Tr>
							<Td colSpan={4} textAlign="center">
								<Text>An error occurred!!</Text>
							</Td>
						</Tr>
					)}
					{data &&
						data.length &&
						data.map((location) => (
							<Tr key={`${location.town}${location.id}`}>
								<Td isNumeric>{counter++}</Td>
								<Td>{location.physicalAddress}</Td>
								<Td>{location.town}</Td>
								<Td isNumeric>{location.zone}</Td>
							</Tr>
						))}
				</Tbody>
			</Table>
		</TableContainer>
	);
};

export default LocationTable;
