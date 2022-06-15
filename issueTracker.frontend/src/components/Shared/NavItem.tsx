import { Flex, FlexProps, Icon, Link } from '@chakra-ui/react';
import { ReactNode } from 'react';
import { IconType } from 'react-icons';
import { Link as RouterLink } from 'react-router-dom';

interface NavItemProps extends FlexProps {
	icon: IconType;
	url: string;
	children: ReactNode;
}
const NavItem = ({ icon, url, children, ...rest }: NavItemProps) => {
	return (
		<Link
			to={url}
			as={RouterLink}
			style={{ textDecoration: 'none' }}
			_hidden={{ backgroundColor: 'whatsapp' }}
			_focus={{ boxShadow: 'none' }}
		>
			<Flex
				align="center"
				p="4"
				mx="4"
				borderRadius="lg"
				role="group"
				cursor="pointer"
				_hover={{
					bg: 'cyan.400',
					color: 'white',
				}}
				{...rest}
			>
				{icon && (
					<Icon
						mr="4"
						fontSize="16"
						_groupHover={{
							color: 'white',
						}}
						as={icon}
					/>
				)}
				{children}
			</Flex>
		</Link>
	);
};

export default NavItem;
