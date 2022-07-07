import { HStack, Stack } from '@chakra-ui/react';
import React, { useState } from 'react';
import InspectionForm from '../../components/inspection/InspectionForm';
import InspectionTable from '../../components/inspection/InspectionTable';
import { IInspection } from '../../type';

const InspectionPage: React.FC = () => {
	const [selectedInspection, setSelectedInspection] =
		useState<IInspection | null>(null);
	return (
		<Stack spacing={'5'}>
			<HStack width={'100%'}>
				<InspectionForm
					selectedInspection={selectedInspection}
					setSelectedInspection={setSelectedInspection}
				/>
			</HStack>
			<InspectionTable setSelectedInspection={setSelectedInspection} />
		</Stack>
	);
};

export default InspectionPage;
