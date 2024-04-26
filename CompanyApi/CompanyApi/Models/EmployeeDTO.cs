namespace CompanyApi.Models
{
    public class EmployeeDTO
    {
        public int EmployeeId { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public int? ManagerId { get; set; }
        public decimal? Salary { get; set; }
        public decimal? Bonus { get; set; }
        public int? DepartmentId { get; set; }

        public static EmployeeDTO EmployeeToDTO(Employee employee) =>
         new EmployeeDTO
         {
             EmployeeId = employee.EmployeeId,
             FirstName = employee.FirstName,
             LastName = employee.LastName,
             ManagerId = employee.ManagerId,
             Salary = employee.Salary,
             Bonus = employee.Bonus,
             DepartmentId = employee.DepartmentId
         };
        public static Employee DTOToEmployee(EmployeeDTO employeeDTO) =>
        new Employee
        {
            EmployeeId = employeeDTO.EmployeeId,
            FirstName = employeeDTO.FirstName,
            LastName = employeeDTO.LastName,
            ManagerId = employeeDTO.ManagerId,
            Salary = employeeDTO.Salary,
            Bonus = employeeDTO.Bonus,
            DepartmentId = employeeDTO.DepartmentId
        };
    }

}
