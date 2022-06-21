import {
	Flex,
	useColorModeValue,
	IconButton,
	HStack,
	Menu,
	MenuButton,
	VStack,
	Box,
	MenuList,
	MenuItem,
	Text,
	FlexProps,
	useColorMode,
	Button,
	Circle,
} from '@chakra-ui/react';
import React, { useContext, useEffect, useState } from 'react';
import { FiMenu, FiChevronDown, FiSun, FiMoon } from 'react-icons/fi';
import { AppContext } from '../../context/AppContextProvider';
import useAuth from '../../hooks/useAuth';
import useAxios from '../../hooks/useAxios';
import { IUser } from '../../type';

interface HeaderProps extends FlexProps {
	onOpen: () => void;
}

export const Header: React.FC<HeaderProps> = ({ onOpen, ...rest }) => {
	const { colorMode, toggleColorMode } = useColorMode();
	const [user, setUser] = useState<IUser>();
	const { appState, changeRole } = useContext(AppContext);
	const { logout } = useAuth();
	const axios = useAxios();

	useEffect(() => {
		axios
			.get('/api/user', { params: { email: appState.email } })
			.then((res) => {
				if (res.status >= 200 && res.status <= 299) {
					const user: IUser = res.data.data;
					setUser(() => user);
					changeRole(user.role.name);
				}
			})
			.catch((err) => {
				if (err.message === 'not authorized' || err.response?.status === 404) {
					logout();
				}
			});
	}, []);
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
				fontSize={{ sm: '2xl', base: '16' }}
				fontFamily="monospace"
				fontWeight="bold"
				textTransform="capitalize"
			>
				issue tracker
			</Text>

			<HStack spacing={{ base: '0', md: '6' }}>
				<Button onClick={toggleColorMode} marginRight={{ md: '0', base: '3' }}>
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
								<Circle bg={'whatsapp.500'} size="40px">
									{user && (
										<Text
											textTransform={'uppercase'}
										>{`${user?.userName.firstName.charAt(
											0
										)} ${user?.userName.lastName.charAt(0)}`}</Text>
									)}
								</Circle>
								<VStack
									display={{ base: 'none', md: 'flex' }}
									alignItems="flex-start"
									spacing="1px"
									ml="2"
								>
									{user && (
										<Text fontSize="sm">
											{`${user?.userName.firstName} ${user?.userName.lastName}`}
										</Text>
									)}
									<Text fontSize="xs" color="gray.600">
										{user?.role.name}
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
							<MenuItem
								_hover={{
									bg: useColorModeValue('whatsapp.600', 'whatsapp.500'),
									color: 'white',
								}}
								onClick={logout}
							>
								Sign out
							</MenuItem>
						</MenuList>
					</Menu>
				</Flex>
			</HStack>
		</Flex>
	);
};
