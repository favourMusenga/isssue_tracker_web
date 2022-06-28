import {
	Alert,
	AlertDescription,
	AlertIcon,
	AlertTitle,
	Stack,
} from '@chakra-ui/react';
import React from 'react';

const DashBoardPage: React.FC = () => {
	return (
		<Stack
			flexDirection={'column'}
			alignItems="center"
			justifyContent="center"
			h="100%"
		>
			<Alert
				status="success"
				variant="subtle"
				flexDirection="column"
				alignItems="center"
				justifyContent="center"
				textAlign="center"
				height="200px"
			>
				<AlertIcon boxSize="40px" mr={0} />
				<AlertTitle mt={4} mb={1} fontSize="lg">
					Zesco Issue Tracker
				</AlertTitle>
				<AlertDescription maxWidth="sm">
					Welcome to the Zesco issue tracker app
				</AlertDescription>
			</Alert>
		</Stack>
	);
};

export default DashBoardPage;
