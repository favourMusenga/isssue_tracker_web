import { Routes, Route } from 'react-router-dom';
import MainLayout from '../components/Shared/MainLayout';
import AboutPage from '../pages/protected/AboutPage';
import AddUserPage from '../pages/protected/AddUserPage';
import DashBoardPage from '../pages/protected/DashBoardPage';
import EquipmentPage from '../pages/protected/EquipmentPage';
import InspectionPage from '../pages/protected/InspectionPage';
import LocationPage from '../pages/protected/LocationPage';
import LogInPage from '../pages/public/LogInPage';
import NotFound from '../pages/public/NotFound';
import AuthRequired from './AuthRequired';

export const AppRoutes = () => {
	return (
		<Routes>
			<Route path="/login" element={<LogInPage />} />
			<Route element={<AuthRequired />}>
				<Route element={<MainLayout />}>
					<Route path="/" element={<DashBoardPage />} />
					<Route path="/equipment" element={<EquipmentPage />} />
					<Route path="/inspection" element={<InspectionPage />} />
					<Route path="/location" element={<LocationPage />} />
					<Route path="/add-user" element={<AddUserPage />} />
					<Route path="/about" element={<AboutPage />} />
				</Route>
			</Route>
			<Route path="*" element={<NotFound />} />
		</Routes>
	);
};
