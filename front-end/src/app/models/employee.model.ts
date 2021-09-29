export class Employee {
    constructor(
        public id: String,
        public firstName: String,
        public lastName: String,
        public email: String,
        public phoneNumber: String,
        public hireDate: any,
        public salary: Number,
        public managerId: String,
        public department_id: String,
    ) {

    }
}