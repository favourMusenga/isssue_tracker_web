export interface IUserRequest {
	email: string;
	password: string;
	role: string;
	firstName: string;
	middleName?: string;
	lastName: string;
}

export interface IEquipmentRequest {
	name: string;
	description: string;
	locationId: number;
}

export interface IInspectionRequest {
	comment: string;
	date: string;
	userEmail: number;
	statusId: number;
	equipmentId: number;
}

export interface IUser {
	id?: number;
	email: string;
	password: string;
	role: IRole;
	userName: IUserName;
}

export interface IUserName {
	firstName: string;
	middleName?: string;
	lastName: string;
}

export interface IRole {
	id?: number;
	name: string;
}

export interface IStatus {
	id?: number;
	name: string;
	description?: string;
}

export interface ILocation {
	id?: number;
	physicalAddress: string;
	zone: number;
	town: string;
}

export interface IEquipment {
	id?: number;
	name: string;
	description: string;
	location: ILocation;
}

export interface IInspection {
	id?: number;
	comment: string;
	date: string;
	appUser: IUser;
	status: IStatus;
	equipment: IEquipment;
}
