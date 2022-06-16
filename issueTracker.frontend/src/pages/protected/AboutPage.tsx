import {
	Center,
	Box,
	useColorModeValue,
	Avatar,
	Heading,
	Stack,
	Badge,
	Text,
} from '@chakra-ui/react';
import React from 'react';

const AboutPage: React.FC = () => {
	return (
		<Center py={6}>
			<Box
				maxW={'320px'}
				w={'full'}
				bg={useColorModeValue('white', 'gray.700')}
				boxShadow={'2xl'}
				rounded={'lg'}
				p={6}
				textAlign={'center'}
			>
				<Avatar size={'2xl'} mb={4} pos={'relative'} />
				<Heading fontSize={'2xl'} fontFamily={'body'}>
					Favour musenga
				</Heading>
				<Text fontWeight={600} color={'gray.500'} mb={4}>
					StudentID: 19139116
				</Text>
				<Text
					textAlign={'center'}
					color={useColorModeValue('gray.700', 'gray.400')}
					px={3}
				>
					Programme: Computer Science
				</Text>

				<Stack align={'center'} justify={'center'} direction={'row'} mt={6}>
					<Badge
						px={2}
						py={1}
						bg={useColorModeValue('gray.50', 'gray.800')}
						fontWeight={'400'}
					>
						#java
					</Badge>
					<Badge
						px={2}
						py={1}
						bg={useColorModeValue('gray.50', 'gray.800')}
						fontWeight={'400'}
					>
						#react
					</Badge>
					<Badge
						px={2}
						py={1}
						bg={useColorModeValue('gray.50', 'gray.800')}
						fontWeight={'400'}
					>
						#sprint boot
					</Badge>
				</Stack>
			</Box>
		</Center>
	);
};

export default AboutPage;
