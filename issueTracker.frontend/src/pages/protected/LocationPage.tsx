import { Container } from '@chakra-ui/react';
import React from 'react';
import LocationForm from '../../components/Location/LocationForm';
import LocationTable from '../../components/Location/LocationTable';

const LocationPage: React.FC = () => {
	return (
		<Container padding="0">
			<LocationForm />

			<LocationTable />
		</Container>
	);
};

export default LocationPage;
