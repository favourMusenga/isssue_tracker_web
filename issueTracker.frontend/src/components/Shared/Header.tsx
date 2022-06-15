import {
	Flex,
	useColorModeValue,
	IconButton,
	HStack,
	Menu,
	MenuButton,
	Avatar,
	VStack,
	Box,
	MenuList,
	MenuItem,
	Text,
	FlexProps,
	useColorMode,
	Button,
} from '@chakra-ui/react';
import React from 'react';
import { FiMenu, FiChevronDown, FiSun, FiMoon } from 'react-icons/fi';

interface HeaderProps extends FlexProps {
	onOpen: () => void;
}

export const Header: React.FC<HeaderProps> = ({ onOpen, ...rest }) => {
	const { colorMode, toggleColorMode } = useColorMode();
	return (
		<Flex
			ml={{ base: 0, md: 60 }}
			px={{ base: 4, md: 4 }}
			height="20"
			alignItems="center"
			bg={useColorModeValue('whatsapp.300', 'gray.700')}
			borderBottomWidth="1px"
			borderBottomColor={useColorModeValue('gray.200', 'gray.700')}
			justifyContent={{ base: 'space-between' }}
			{...rest}
		>
			<IconButton
				display={{ base: 'flex', md: 'none' }}
				onClick={onOpen}
				variant="outline"
				aria-label="open menu"
				icon={<FiMenu />}
			/>

			<Text
				fontSize="2xl"
				fontFamily="monospace"
				fontWeight="bold"
				textTransform="capitalize"
			>
				issue tracker
			</Text>

			<HStack spacing={{ base: '0', md: '6' }}>
				<Button onClick={toggleColorMode}>
					{colorMode === 'light' ? <FiMoon /> : <FiSun />}
				</Button>
				<Flex alignItems={'center'}>
					<Menu>
						<MenuButton
							py={2}
							transition="all 0.3s"
							_focus={{ boxShadow: 'none' }}
						>
							<HStack>
								<Avatar
									size={'sm'}
									src={
										'https://images.unsplash.com/photo-1619946794135-5bc917a27793?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=200&w=200&s=b616b2c5b373a80ffc9636ba24f7a4a9'
									}
								/>
								<VStack
									display={{ base: 'none', md: 'flex' }}
									alignItems="flex-start"
									spacing="1px"
									ml="2"
								>
									<Text fontSize="sm">Justina Clark</Text>
									<Text fontSize="xs" color="gray.600">
										Admin
									</Text>
								</VStack>
								<Box display={{ base: 'none', md: 'flex' }}>
									<FiChevronDown />
								</Box>
							</HStack>
						</MenuButton>
						<MenuList
							bg={useColorModeValue('white', 'gray.900')}
							borderColor={useColorModeValue('gray.200', 'gray.700')}
						>
							<MenuItem>Sign out</MenuItem>
						</MenuList>
					</Menu>
				</Flex>
			</HStack>
		</Flex>
	);
};
