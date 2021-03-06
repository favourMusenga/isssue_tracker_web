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
import useAppContext from '../../hooks/useAppContext';
import useAxios from '../../hooks/useAxios';
import { IInspection } from '../../type';

interface InspectionTableProps {
	setSelectedInspection: React.Dispatch<
		React.SetStateAction<IInspection | null>
	>;
}

const InspectionTable: React.FC<InspectionTableProps> = (props) => {
	let counter = 1;
	const axios = useAxios();
	const {
		appState: { email },
	} = useAppContext();

	async function getInspection(): Promise<IInspection[]> {
		const res = await axios.get('/api/inspection', { params: { email } });
		const resData = res.data.data;

		return resData;
	}

	const { data, isLoading, isError } = useQuery(
		'fetch-inspection',
		getInspection
	);

	function onSelectInspection(inspection: IInspection) {
		props.setSelectedInspection(() => inspection);
	}
	return (
		<TableContainer
			marginTop={'10'}
			bg={useColorModeValue('white', 'gray.700')}
		>
			<Table variant="simple" colorScheme="whatsapp">
				<Thead>
					<Tr>
						<Th isNumeric>No</Th>
						<Th>Inspected by</Th>
						<Th>Equipment</Th>
						<Th>Status</Th>
						<Th>Comment</Th>
						<Th>Date</Th>
					</Tr>
				</Thead>
				<Tbody>
					{data?.length === 0 && (
						<Tr>
							<Td colSpan={6} textAlign="center">
								<Text> no data to show</Text>
							</Td>
						</Tr>
					)}

					{isLoading && (
						<Tr>
							<Td colSpan={6} textAlign="center">
								<Spinner colorScheme="whatsapp" />
							</Td>
						</Tr>
					)}
					{isError && (
						<Tr>
							<Td colSpan={6} textAlign="center">
								<Text>An error occurred!!</Text>
							</Td>
						</Tr>
					)}
					{data &&
						data.length > 0 &&
						data.map((inspection) => (
							<Tr
								key={`${inspection.id}`}
								style={{ cursor: 'pointer' }}
								_hover={{
									background: useColorModeValue('gray.200', 'gray.600'),
								}}
								onClick={() => onSelectInspection(inspection)}
							>
								<Td isNumeric>{counter++}</Td>
								<Td>{`${inspection.appUser.userName.firstName} ${inspection.appUser.userName.lastName}`}</Td>
								<Td>{inspection.equipment.name}</Td>
								<Td>{inspection.status.name}</Td>
								<Td>{inspection.comment || ' - '}</Td>
								<Td>{inspection.date}</Td>
							</Tr>
						))}
				</Tbody>
			</Table>
		</TableContainer>
	);
};

export default InspectionTable;
