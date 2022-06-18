import {
	Box,
	BoxProps,
	CloseButton,
	Flex,
	Text,
	useColorModeValue,
} from '@chakra-ui/react';
import { IconType } from 'react-icons';
import { FiTool, FiHome, FiInfo, FiList, FiUser, FiMap } from 'react-icons/fi';
import NavItem from './NavItem';

interface LinkItemProps {
	name: string;
	icon: IconType;
	url: string;
}
const LinkItems: Array<LinkItemProps> = [
	{ name: 'Dashboard', icon: FiHome, url: '/' },
	{ name: 'Inspection', icon: FiList, url: '/inspection' },
	{ name: 'Equipment', icon: FiTool, url: '/equipment' },
	{ name: 'Locations', icon: FiMap, url: '/location' },
	{ name: 'User', icon: FiUser, url: '/add-user' },
	{ name: 'About', icon: FiInfo, url: '/about' },
];

interface SidebarProps extends BoxProps {
	onClose: () => void;
}

const SidebarContent = ({ onClose, ...rest }: SidebarProps) => {
	return (
		<Box
			transition="3s ease"
			bg={useColorModeValue('white', 'gray.900')}
			borderRight="1px"
			borderRightColor={useColorModeValue('gray.200', 'gray.700')}
			w={{ base: 'full', md: 60 }}
			pos="fixed"
			h="full"
			{...rest}
		>
			<Flex h="20" alignItems="center" mx="8" justifyContent="space-between">
				<Text fontSize="2xl" fontFamily="monospace" fontWeight="bold">
					Zesco
				</Text>
				<CloseButton display={{ base: 'flex', md: 'none' }} onClick={onClose} />
			</Flex>
			{LinkItems.map((link) => (
				<NavItem
					key={link.name}
					icon={link.icon}
					url={link.url}
					onClick={onClose}
				>
					{link.name}
				</NavItem>
			))}
		</Box>
	);
};

export default SidebarContent;
