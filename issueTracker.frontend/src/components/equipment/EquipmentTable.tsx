import {
	TableContainer,
	useColorModeValue,
	Table,
	Thead,
	Tr,
	Th,
	Tbody,
	Td,
	Spinner,
	Text,
} from '@chakra-ui/react';
import React from 'react';
import { useQuery } from 'react-query';
import useAxios from '../../hooks/useAxios';
import { IEquipment } from '../../type';

const EquipmentTable: React.FC = () => {
	let counter = 1;
	const axios = useAxios();

	async function getEquipment(): Promise<IEquipment[]> {
		const res = await axios.get('/api/equipment');
		const resData = res.data.data;

		return resData;
	}

	const { data, isLoading, isError } = useQuery(
		'fetch-equipment',
		getEquipment
	);
	return (
		<TableContainer
			marginTop={'10'}
			bg={useColorModeValue('white', 'gray.700')}
		>
			<Table variant="simple" colorScheme="whatsapp">
				<Thead>
					<Tr>
						<Th isNumeric>No</Th>
						<Th>Name</Th>
						<Th>Physical address</Th>
						<Th>Description</Th>
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
						data.length > 0 &&
						data.map((equipment) => (
							<Tr key={`${equipment.id}`}>
								<Td isNumeric>{counter++}</Td>
								<Td>{equipment.name}</Td>
								<Td>{equipment.location.physicalAddress}</Td>
								<Td>{equipment.description || ' - '}</Td>
							</Tr>
						))}
				</Tbody>
			</Table>
		</TableContainer>
	);
};

export default EquipmentTable;
