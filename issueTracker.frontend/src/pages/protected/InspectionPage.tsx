import { HStack, Stack } from '@chakra-ui/react';
import React from 'react';
import InspectionForm from '../../components/inspection/InspectionForm';
import InspectionTable from '../../components/inspection/InspectionTable';

const InspectionPage: React.FC = () => {
	return (
		<Stack spacing={'5'}>
			<HStack width={'100%'}>
				<InspectionForm />
			</HStack>
			<InspectionTable />
		</Stack>
	);
};

export default InspectionPage;
