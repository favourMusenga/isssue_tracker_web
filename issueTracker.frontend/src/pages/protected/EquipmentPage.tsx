import { Container, HStack, Stack } from '@chakra-ui/react';
import React from 'react';
import EquipmentForm from '../../components/equipment/EquipmentForm';
import EquipmentTable from '../../components/equipment/EquipmentTable';

const EquipmentPage: React.FC = () => {
	return (
		<Stack spacing={'5'}>
			<HStack width={'100%'}>
				<EquipmentForm />
			</HStack>
			<EquipmentTable />
		</Stack>
	);
};

export default EquipmentPage;
