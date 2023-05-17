

import com.imconsalting.projekat.employee.Employee;

import java.util.List;
/*
public class CustomerPanel extends JPanel {
    private JTable customerTable;
    private List<Customer> customerList;
    private final JTextField nameTextField = new JTextField();
    private final JLabel nameLabel = new JLabel("Name: ", SwingConstants.TRAILING);
    private final JTextField surnameTextField = new JTextField();
    private final JLabel surnameLabel = new JLabel("Surname", SwingConstants.TRAILING);
    private final JButton buttonAddEmployee = new JButton("Add employee");
    private final JButton buttonDeleteEmployee=new JButton("Delete Employee");



    public CustomerPanel() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        actionComponents();
    }

    private void actionComponents() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT c FROM Customer c");
        customerList = query.getResultList();
        entityManager.getTransaction().commit();

        CustomerTableModel customerTableModel=new CustomerTableModel();
        customerTable = new JTable(customerTableModel);
        JScrollPane employeeTableScrollPane = new JScrollPane(customerTable);
        add(employeeTableScrollPane);

        JPanel namePanel = new JPanel();
        namePanel.add(nameLabel);
        nameTextField.setEditable(true);
        nameTextField.setColumns(10);
        namePanel.add(nameTextField);

        JPanel surnamePanel = new JPanel();
        surnamePanel.add(surnameLabel);
        surnameTextField.setEditable(true);
        surnameTextField.setColumns(10);
        surnamePanel.add(surnameTextField);

        JPanel buttonPanel = new JPanel();
        buttonAddEmployee.addActionListener(this::ButtonAddListener);
        buttonPanel.add(buttonAddEmployee);

        buttonDeleteEmployee.addActionListener(this::ButtonDeleteListener);

        add(namePanel);
        add(surnamePanel);
        add(buttonPanel);
        add(buttonDeleteEmployee);



    }

    private void ButtonDeleteListener(ActionEvent event){
        Customer customer=customerList.get(customerTable.getSelectedRow());
        EntityManagerFactory entityManagerFactory =Persistence.createEntityManagerFactory("projectPU");
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(customer);
        entityManager.getTransaction().commit();
    }

    private void ButtonAddListener(ActionEvent event){
        EntityManagerFactory entityManagerFactory =Persistence.createEntityManagerFactory("projectPU");
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Employee employee=new Employee();
        employee.setName(nameTextField.getText());
        employee.setSurname(surnameTextField.getText());
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        nameTextField.setText("");
        surnameTextField.setText("");
    }

    private class CustomerTableModel extends AbstractTableModel {

        private List<String> columnNames = new ArrayList<>();

        public CustomerTableModel() {

            columnNames.add("id");
            columnNames.add("name");
            columnNames.add("surname");
            columnNames.add("birthday");
            columnNames.add("address");
            columnNames.add("mobile");
            columnNames.add("email");
            columnNames.add("empstatus");
            columnNames.add("profession");
            columnNames.add("company");
            columnNames.add("employee");
            columnNames.add("date registry");
            //this.columnNames = playerDao.getColumnNames();
        }

        @Override
        public int getRowCount() {
            return customerList.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Customer customer= customerList.get(rowIndex);
            //EmployeeWrapper employeeWrapper = new EmployeeWrapper(employee);
            //Object employeeFieldOnIndex = employeeWrapper.getColumValue(columnIndex);
            CustomerWrapper customerWrapper=new CustomerWrapper(customer);
            return customerWrapper.getColumValue(columnIndex);
        }

        /**
         * @param aValue      value to assign to cell
         * @param rowIndex    row of cell
         * @param columnIndex column of cell
         *
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            /*EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("projectPU");
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            Customer customer=customerList.get(rowIndex);
            CustomerWrapper customerWrapper=new CustomerWrapper(customer);
            customerWrapper.setColumnValue(aValue,columnIndex);
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();*
        }


    }
}
*/