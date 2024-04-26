namespace CompanyApi.Models
{
    public class DepartmentDTO
    {
        public int DepartmentId { get; set; }
        public string Name { get; set; }

        public static DepartmentDTO DepartmentToDTO(Department department)
        {
            var departmentDTO = new DepartmentDTO
            {
                DepartmentId = department.DepartmentId,
                Name = department.Name
            };


            return departmentDTO;
        }

        public static Department DTOToDepartment(DepartmentDTO departmentDTO)
        {
            var department = new Department
            {
                DepartmentId = departmentDTO.DepartmentId,
                Name = departmentDTO.Name,
            };

            return department;
        }
    }
}
